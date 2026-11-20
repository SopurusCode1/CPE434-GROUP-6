# Scrum charter: HebronMall team



## Product Goal

Students on campus can see which items from every campus bookstore and the campus shopping mall are in stock, order and pay for them online, and have them delivered to their hostel.

## Roles for Sprint 1 (rotate each Sprint)

- **Product Owner:** [SBU]. Speaks for the students and shop staff, and decides the order of the Product Backlog.
- **Scrum Master:** [Sopuruchi]. Keeps the events on time, keeps the board current, and chases blockers. Not a boss.
- **Developers:** [Demilade, Jethera, Chimdalu, Shadrach, Sopuruchi]. Everyone builds, including the Product Owner and Scrum Master.

<!-- | Sprint | Product Owner | Scrum Master |
|---|---|---|
| 1 | [name] | [name] |
| 2 | [name] | [name] |
| 3 | [name] | [name] | -->

## Rhythm

- **Sprint:** 2 weeks
- **Sprint Planning:** about 30 minutes at the start of each Sprint. Choose the Sprint Goal and the cards for it.
- **Daily Scrum:** 10 minutes, 3 times a week: [Saturdays, Sundays], [12pm], [Telegram]. Each person answers: what did I finish, what am I doing next, what is blocking me?
- **Sprint Review:** in the lab. Show the working Increment and collect feedback.
- **Retrospective:** 15 minutes. What went well, what did not, and one thing to change next Sprint.

## Artifacts and commitments

| Artifact | Where it lives | Commitment |
|---|---|---|
| Product Backlog | GitHub Projects board, To do column (ordered) | Product Goal |
| Sprint Backlog | Cards chosen at Sprint Planning | Sprint Goal |
| Increment | `main` branch of the team repository | Definition of Done |

**Sprint 1 goal:** [one sentence, set at Sprint Planning]

## Definition of Done

A card is Done only when all five are true:

- [ ] Compiles and runs on a teammate's machine
- [ ] Built on a branch and merged through a pull request
- [ ] At least one teammate reviewed and approved it
- [ ] Meets the card's acceptance check
- [ ] README says how to run it

## Kanban board (GitHub Projects)

| To do | In progress (max 3) | In review | Done |
|---|---|---|---|

- **Visualise:** every piece of work is a card with a name on it.
- **Limit work in progress:** at most 3 cards in *In progress*. Finish before starting something new.
- **Pull, don't push:** when a slot frees up, take the next card from *To do*, in order.

### Starting feature cards

1. Browse the catalogue and see stock status (only in-stock items shown as orderable)
2. Search and filter products by name, category, price and shop
3. Register and log in
4. Add to cart
5. Place an order with a delivery hostel address
6. Pay online through the payment gateway
7. Shop staff update products and stock
8. Track order status until delivered to the hostel

*These become user stories with acceptance criteria in Week 4.*

## Working agreements

- Nobody pushes straight to `main`. Work on a branch and open a pull request.
- Review a teammate's pull request before starting a new card.
- Write the person's name on the card when you start it.
- Raise blockers at the Daily Scrum, or in the team chat the same day.
- Commit your own changes, so the history shows who did what.
- Be on time to events. If you cannot attend, tell the Scrum Master beforehand.
