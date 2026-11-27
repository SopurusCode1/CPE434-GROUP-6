package com.hebronmall.ecommerce_api.service;

import com.hebronmall.ecommerce_api.dto.CheckoutRequest;
import com.hebronmall.ecommerce_api.dto.OrderItemRequest;
import com.hebronmall.ecommerce_api.dto.OrderItemResponse;
import com.hebronmall.ecommerce_api.dto.OrderResponse;
import com.hebronmall.ecommerce_api.exception.BadRequestException;
import com.hebronmall.ecommerce_api.exception.ResourceNotFoundException;
import com.hebronmall.ecommerce_api.model.*;
import com.hebronmall.ecommerce_api.repository.OrderRepository;
import com.hebronmall.ecommerce_api.repository.ProductRepository;
import com.hebronmall.ecommerce_api.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final StudentRepository studentRepository;

    @Transactional
    public OrderResponse checkout(String studentEmail, CheckoutRequest request) {
        Student student = studentRepository.findByEmail(studentEmail)
                .orElseThrow(() -> new ResourceNotFoundException("Only registered students can place orders"));

        Order order = Order.builder()
                .student(student)
                .deliveryAddress(request.deliveryAddress())
                .status(OrderStatus.PENDING)
                .totalAmount(BigDecimal.ZERO)
                .build();

        BigDecimal total = BigDecimal.ZERO;

        for (OrderItemRequest itemRequest : request.items()) {
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + itemRequest.productId()));

            if (product.getStockQuantity() < itemRequest.quantity()) {
                throw new BadRequestException(
                        "Insufficient stock for '" + product.getName() + "'. Available: " + product.getStockQuantity()
                );
            }

            // Deduct inventory
            product.setStockQuantity(product.getStockQuantity() - itemRequest.quantity());
            productRepository.save(product);

            // Create OrderItem with price snapshot
            OrderItem orderItem = OrderItem.builder()
                    .order(order)
                    .product(product)
                    .quantity(itemRequest.quantity())
                    .unitPrice(product.getPrice())
                    .build();

            order.getItems().add(orderItem);

            BigDecimal itemSubtotal = product.getPrice().multiply(BigDecimal.valueOf(itemRequest.quantity()));
            total = total.add(itemSubtotal);
        }

        order.setTotalAmount(total);
        Order savedOrder = orderRepository.save(order);

        return mapToResponse(savedOrder);
    }

    @Transactional(readOnly = true)
    public List<OrderResponse> getMyOrders(String studentEmail) {
        return orderRepository.findByStudentEmailOrderByCreatedAtDesc(studentEmail).stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));
        return mapToResponse(order);
    }

    private OrderResponse mapToResponse(Order order) {
        List<OrderItemResponse> itemResponses = order.getItems().stream()
                .map(item -> {
                    BigDecimal subtotal = item.getUnitPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                    return new OrderItemResponse(
                            item.getProduct().getId(),
                            item.getProduct().getName(),
                            item.getProduct().getStore().getName(),
                            item.getQuantity(),
                            item.getUnitPrice(),
                            subtotal
                    );
                })
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getStudent().getFullName(),
                order.getStudent().getMatricNumber(),
                order.getDeliveryAddress(),
                order.getStatus(),
                order.getTotalAmount(),
                itemResponses,
                order.getCreatedAt()
        );
    }
    @Transactional
    public OrderResponse updateOrderStatus(Long orderId, OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with ID: " + orderId));

        if (order.getStatus() == OrderStatus.DELIVERED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new BadRequestException("Cannot change status of an order that is already " + order.getStatus());
        }

        // If the order is being cancelled, return the reserved quantities back to product stock
        if (newStatus == OrderStatus.CANCELLED) {
            for (OrderItem item : order.getItems()) {
                Product product = item.getProduct();
                product.setStockQuantity(product.getStockQuantity() + item.getQuantity());
                productRepository.save(product);
            }
        }

        order.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);

        return mapToResponse(updatedOrder);
    }
}
