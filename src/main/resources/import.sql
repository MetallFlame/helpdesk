INSERT INTO User (user_id, first_name, last_name, role, email, password) VALUES (1, 'employee1','test', 0,'user1_mogilev@yopmail.com', 'P@ssword1');
INSERT INTO User (user_id, first_name, last_name, role, email, password) VALUES (2, 'employee2','test', 0,'user2_mogilev@yopmail.com', 'P@ssword1');
INSERT INTO User (first_name, last_name, role, email, password) VALUES ('manager1','test', 1,'manager1_mogilev@yopmail.com', 'P@ssword1');
INSERT INTO User (first_name, last_name, role, email, password) VALUES ('manager2','test', 1,'manager2_mogilev@yopmail.com', 'P@ssword1');
INSERT INTO User (first_name, last_name, role, email, password) VALUES ('engineer1','test', 2,'engineer1_mogilev@yopmail.com', 'P@ssword1');
INSERT INTO User (first_name, last_name, role, email, password) VALUES ('engineer2','test', 2,'engineer2_mogilev@yopmail.com', 'P@ssword1');
INSERT INTO Category (name) VALUES ('Application & Services');
INSERT INTO Category (name) VALUES ('Benefits & Paper Work');
INSERT INTO Category (name) VALUES ('Hardware & Software');
INSERT INTO Category (name) VALUES ('People Management');
INSERT INTO Category (name) VALUES ('Security & Access');
INSERT INTO Category (name) VALUES ('Workplaces & Facilities');
INSERT INTO Ticket (name, description, state, urgency, ticket_category_id, created_on, desired_resolution_date, owner_id) VALUES ('first ticket', 'test ticket from owner user with id=1', 0, 1, 1,'2020-09-09', '2021-11-20', 1);
INSERT INTO Ticket (name, description, state, urgency, ticket_category_id, created_on, desired_resolution_date, owner_id) VALUES ('second ticket', 'test ticket from owner user with id=1', 0, 1, 2,'2020-09-11', '2021-11-27', 1);
INSERT INTO Ticket (name, description, state, urgency, ticket_category_id, created_on, desired_resolution_date, owner_id) VALUES ('third ticket', 'test ticket from owner user with id=1', 0, 1, 3,'2020-09-07', '2021-12-30', 1);
INSERT INTO Ticket (name, description, state, urgency, ticket_category_id, created_on, desired_resolution_date, owner_id) VALUES ('first ticket', 'test ticket from owner user with id=2', 0, 1, 4,'2020-09-10', '2021-12-13', 2);
INSERT INTO Ticket (name, description, state, urgency, ticket_category_id, created_on, desired_resolution_date, owner_id) VALUES ('second ticket', 'test ticket from owner user with id=2', 0, 1, 5,'2020-08-09', '2021-12-22', 2);
INSERT INTO Comment (text, date, major_ticket_id, commentator_id) VALUES ('test comment 1', '2020-09-09', 1, 1);
INSERT INTO Comment (text, date, major_ticket_id, commentator_id) VALUES ('test comment 2', '2020-10-11', 1, 1);
INSERT INTO Comment (text, date, major_ticket_id, commentator_id) VALUES ('test comment 3', '2020-10-12', 1, 1);