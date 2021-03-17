create schema if not exists ecomm;

create TABLE IF NOT EXISTS ecomm.product (
	id uuid NOT NULL DEFAULT random_uuid(),
	name varchar(56) NOT NULL,
	description varchar(200),
	price numeric(16, 4) DEFAULT 0 NOT NULL,
	count numeric(8, 0),
	image_url varchar(40),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.tag (
	id uuid NOT NULL DEFAULT random_uuid(),
	name varchar(20),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.product_tag (
	product_id uuid NOT NULL DEFAULT random_uuid(),
	tag_id uuid NOT NULL,
	FOREIGN KEY (product_id)
		REFERENCES product(id),
	FOREIGN KEY(tag_id)
		REFERENCES tag(id)
);

insert into ecomm.product values ('6d62d909-f957-430e-8689-b5129c0bb75e', 'Antifragile', 'Antifragile - Things that gains from disorder. By Nassim Nicholas Taleb', 17.15, 33, '/images/Antifragile.jpg');
insert into ecomm.product values ('a0a4f044-b040-410d-8ead-4de0446aec7e', 'Sapiens', 'Sapiens - A brief history of mankind. By Yuval Noah Harari', 7.99, 115, '/images/Sapiens.jpeg');
insert into ecomm.product values ('808a2de1-1aaa-4c25-a9b9-6612e8f29a38', 'Thinking Fast and Slow', 'Thinking Fast and Slow. By winner of Nobel Prize - Danniel Kahneman', 17.32, 738, '/images/ThinkingFast.jpeg');
insert into ecomm.product values ('510a0d7e-8e83-4193-b483-e27e09ddc34d', 'How to Fail at Almost Everything and Still Win Big', 'How to Fail at Almost Everything and Still Win Big - Kind of Story of My Life. By Scott Adams', 15.00, 820, '/images/HowToFail.jpeg');
insert into ecomm.product values ('03fef6ac-1896-4ce8-bd69-b798f85c6e0b', 'Influence', 'Influence - Psychology of Persuasion. By Robert B. Cialdini Ph.D.', 99.99, 1, '/images/Influence.jpeg');
insert into ecomm.product values ('d3588630-ad8e-49df-bbd7-3167f7efb246', 'Poor Charlie''s Almanack', 'Poor Charlie''s Almanack - Wits and Wisdom of Charles T. Munger. By Peter Kauffman', 10.99, 801, '/images/PoorCharlie.jpeg');
insert into ecomm.product values ('819e1fbf-8b7e-4f6d-811f-693534916a8b', 'Hackers & Painters', 'Hackers & Painters - Big Ideas from Computer Age. By Paul Graham', 14, 808, '/images/Hackers.jpg');
insert into ecomm.product values ('3395a42e-2d88-40de-b95f-e00e1502085b', 'Seeking Wisdom', 'Seeking Wisdom - From Darwin To Mungar. By Peter Bevelin',  12, 127, '/images/Seeking.jpg');
insert into ecomm.product values ('3395a43e-2d88-40de-b95f-e00e1502085b', 'Thinking In Bets', 'Thinking In Bets - Making Smarter Decision. When you don''t have all the facts. By Annie Duke', 18, 438, '/images/ThinkingIn.jpg');
insert into ecomm.product values ('837ab141-399e-4c1f-9abc-bace40296bac', 'Zero to One', 'Zero to One - Notes on Startups, Or How to build future. Peter Theil', 15, 175, '/images/ZeroToOne.jpg');

insert into ecomm.tag (id, name) values ('00000000-b5c6-4896-987c-f30f3678f601', 'book');
insert into ecomm.tag (id, name) values ('00000000-b5c6-4896-987c-f30f3678f602', 'psychology');
insert into ecomm.tag (id, name) values ('00000000-b5c6-4896-987c-f30f3678f603', 'influence');
insert into ecomm.tag (id, name) values ('00000000-b5c6-4896-987c-f30f3678f604', 'wisdom');
insert into ecomm.tag (id, name) values ('00000000-b5c6-4896-987c-f30f3678f605', 'startup');
insert into ecomm.tag (id, name) values ('00000000-b5c6-4896-987c-f30f3678f606', 'investing');
insert into ecomm.tag (id, name) values ('00000000-b5c6-4896-987c-f30f3678f607', 'lessions');
insert into ecomm.tag (id, name) values ('00000000-b5c6-4896-987c-f30f3678f608', 'history');
insert into ecomm.tag (id, name) values ('00000000-b5c6-4896-987c-f30f3678f609', 'howto');
insert into ecomm.tag (id, name) values ('00000000-b5c6-4896-987c-f30f3678f610', 'journey');
insert into ecomm.tag (id, name) values ('00000000-b5c6-4896-987c-f30f3678f611', 'decision');

insert into ecomm.product_tag values ('6d62d909-f957-430e-8689-b5129c0bb75e', '00000000-b5c6-4896-987c-f30f3678f602');
insert into ecomm.product_tag values ('6d62d909-f957-430e-8689-b5129c0bb75e', '00000000-b5c6-4896-987c-f30f3678f601');
insert into ecomm.product_tag values ('a0a4f044-b040-410d-8ead-4de0446aec7e', '00000000-b5c6-4896-987c-f30f3678f608');
insert into ecomm.product_tag values ('a0a4f044-b040-410d-8ead-4de0446aec7e', '00000000-b5c6-4896-987c-f30f3678f601');
insert into ecomm.product_tag values ('808a2de1-1aaa-4c25-a9b9-6612e8f29a38', '00000000-b5c6-4896-987c-f30f3678f602');
insert into ecomm.product_tag values ('808a2de1-1aaa-4c25-a9b9-6612e8f29a38', '00000000-b5c6-4896-987c-f30f3678f601');
insert into ecomm.product_tag values ('808a2de1-1aaa-4c25-a9b9-6612e8f29a38', '00000000-b5c6-4896-987c-f30f3678f607');
insert into ecomm.product_tag values ('808a2de1-1aaa-4c25-a9b9-6612e8f29a38', '00000000-b5c6-4896-987c-f30f3678f611');
insert into ecomm.product_tag values ('510a0d7e-8e83-4193-b483-e27e09ddc34d', '00000000-b5c6-4896-987c-f30f3678f602');
insert into ecomm.product_tag values ('510a0d7e-8e83-4193-b483-e27e09ddc34d', '00000000-b5c6-4896-987c-f30f3678f601');
insert into ecomm.product_tag values ('510a0d7e-8e83-4193-b483-e27e09ddc34d', '00000000-b5c6-4896-987c-f30f3678f610');
insert into ecomm.product_tag values ('03fef6ac-1896-4ce8-bd69-b798f85c6e0b', '00000000-b5c6-4896-987c-f30f3678f602');
insert into ecomm.product_tag values ('03fef6ac-1896-4ce8-bd69-b798f85c6e0b', '00000000-b5c6-4896-987c-f30f3678f603');
insert into ecomm.product_tag values ('d3588630-ad8e-49df-bbd7-3167f7efb246', '00000000-b5c6-4896-987c-f30f3678f604');
insert into ecomm.product_tag values ('d3588630-ad8e-49df-bbd7-3167f7efb246', '00000000-b5c6-4896-987c-f30f3678f607');
insert into ecomm.product_tag values ('819e1fbf-8b7e-4f6d-811f-693534916a8b', '00000000-b5c6-4896-987c-f30f3678f605');
insert into ecomm.product_tag values ('819e1fbf-8b7e-4f6d-811f-693534916a8b', '00000000-b5c6-4896-987c-f30f3678f606');
insert into ecomm.product_tag values ('819e1fbf-8b7e-4f6d-811f-693534916a8b', '00000000-b5c6-4896-987c-f30f3678f607');
insert into ecomm.product_tag values ('3395a42e-2d88-40de-b95f-e00e1502085b', '00000000-b5c6-4896-987c-f30f3678f604');
insert into ecomm.product_tag values ('3395a42e-2d88-40de-b95f-e00e1502085b', '00000000-b5c6-4896-987c-f30f3678f607');
insert into ecomm.product_tag values ('3395a43e-2d88-40de-b95f-e00e1502085b', '00000000-b5c6-4896-987c-f30f3678f611');
insert into ecomm.product_tag values ('3395a43e-2d88-40de-b95f-e00e1502085b', '00000000-b5c6-4896-987c-f30f3678f609');
insert into ecomm.product_tag values ('837ab141-399e-4c1f-9abc-bace40296bac', '00000000-b5c6-4896-987c-f30f3678f609');
insert into ecomm.product_tag values ('837ab141-399e-4c1f-9abc-bace40296bac', '00000000-b5c6-4896-987c-f30f3678f605');
insert into ecomm.product_tag values ('837ab141-399e-4c1f-9abc-bace40296bac', '00000000-b5c6-4896-987c-f30f3678f601');

create TABLE IF NOT EXISTS ecomm.user (
	id uuid NOT NULL DEFAULT random_uuid(),
	username varchar(16),
	password varchar(72),
	first_name varchar(16),
	last_name varchar(16),
	email varchar(24),
	phone varchar(24),
	user_status varchar(16) NOT NULL DEFAULT 'ACTIVE' NULL_TO_DEFAULT,
	role varchar(16) NOT NULL DEFAULT 'ROLE_USER' NULL_TO_DEFAULT,
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.user_token (
	id uuid NOT NULL DEFAULT random_uuid(),
	refresh_token varchar(128),
	user_id uuid NOT NULL,
	PRIMARY KEY(id),
	FOREIGN KEY (user_id)
		REFERENCES ecomm.user(id)
);

create TABLE IF NOT EXISTS ecomm.address (
	id uuid NOT NULL DEFAULT random_uuid(),
	number varchar(24),
	residency varchar(32),
	street varchar(32),
	city varchar(24),
	state varchar(24),
	country varchar(24),
	pincode varchar(10),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.user_address (
	user_id uuid NOT NULL DEFAULT random_uuid(),
	address_id uuid NOT NULL,
	FOREIGN KEY (user_id)
		REFERENCES ecomm.user(id),
	FOREIGN KEY(address_id)
		REFERENCES ecomm.address(id)
);

create TABLE IF NOT EXISTS ecomm.payment (
	id uuid NOT NULL DEFAULT random_uuid(),
	authorized boolean,
	message varchar(64),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.card (
	id uuid NOT NULL DEFAULT random_uuid(),
	number varchar(16),
	user_id uuid NOT NULL UNIQUE,
	last_name varchar(16),
	expires varchar(5),
	cvv varchar(4),
	FOREIGN KEY(user_id)
		REFERENCES ecomm.user(id),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.shipment (
	id uuid NOT NULL DEFAULT random_uuid(),
	est_delivery_date timestamp,
	carrier varchar(24),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.orders (
	id uuid NOT NULL DEFAULT random_uuid(),
	customer_id uuid NOT NULL,
	address_id uuid NOT NULL,
	card_id uuid,
	order_date timestamp,
	total numeric(16, 4) DEFAULT 0 NOT NULL,
	payment_id uuid,
	shipment_id uuid,
	status varchar(24),
	PRIMARY KEY(id),
	FOREIGN KEY(customer_id)
		REFERENCES ecomm.user(id),
	FOREIGN KEY(address_id)
		REFERENCES ecomm.address(id),
	FOREIGN KEY(card_id)
		REFERENCES ecomm.card(id),
	FOREIGN KEY(payment_id)
		REFERENCES ecomm.payment(id),
  FOREIGN KEY(shipment_id)
		REFERENCES ecomm.shipment(id),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.item (
	id uuid NOT NULL DEFAULT random_uuid(),
	product_id uuid NOT NULL,
	quantity numeric(8, 0),
	unit_price numeric(16, 4) NOT NULL,
  FOREIGN KEY(product_id)
		REFERENCES ecomm.product(id),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.order_item (
  id uuid NOT NULL DEFAULT random_uuid(),
	order_id uuid NOT NULL,
	item_id uuid NOT NULL,
	FOREIGN KEY (order_id)
		REFERENCES ecomm.orders(id),
	FOREIGN KEY(item_id)
		REFERENCES ecomm.item(id)
);

create TABLE IF NOT EXISTS ecomm.authorization (
  id uuid NOT NULL DEFAULT random_uuid(),
	order_id uuid NOT NULL DEFAULT random_uuid(),
	authorized boolean,
	time timestamp,
	message varchar(16),
	error varchar(24),
	FOREIGN KEY (order_id)
		REFERENCES ecomm.orders(id),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.cart (
  id uuid NOT NULL DEFAULT random_uuid(),
	user_id uuid NOT NULL DEFAULT random_uuid(),
	FOREIGN KEY (user_id)
		REFERENCES ecomm.user(id),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.cart_item (
	cart_id uuid NOT NULL DEFAULT random_uuid(),
	item_id uuid NOT NULL DEFAULT random_uuid(),
	FOREIGN KEY (cart_id)
		REFERENCES ecomm.cart(id),
	FOREIGN KEY(item_id)
		REFERENCES ecomm.item(id)
);

insert into ecomm.user (id, username, password, first_name, last_name, email, phone, user_status, role) values('a1b9b31d-e73c-4112-af7c-b68530f38222', 'scott', '{bcrypt}$2a$10$neR0EcYY5./tLVp4litNyuBy/kfrTsqEv8hiyqEKX0TXIQQwC/5Rm', 'Bruce', 'Scott', 'bruce@scott.db', '234234234', 'ACTIVE', 'USER');
insert into ecomm.user (id, username, password, first_name, last_name, email, phone, user_status, role) values('a1b9b31d-e73c-4112-af7c-b68530f38223', 'scott2', '{bcrypt}$2a$10$neR0EcYY5./tLVp4litNyuBy/kfrTsqEv8hiyqEKX0TXIQQwC/5Rm', 'Bruce', 'Scott', 'bruce2@scott.db', '234234234', 'ACTIVE', 'ADMIN');
INSERT INTO ecomm.address VALUES ('a731fda1-aaad-42ea-bdbc-a27eeebe2cc0', '9I-999', 'Fraser Suites Le Claridge', 'Champs-Elysees', 'Paris', 'Île-de-France', 'France', '75008');
insert into ecomm.user_address values ('a1b9b31d-e73c-4112-af7c-b68530f38222', 'a731fda1-aaad-42ea-bdbc-a27eeebe2cc0');
INSERT INTO ecomm.card VALUES ('618ffaff-cbcd-48d4-8848-a15601e6725b', '999-999-999-999', 'a1b9b31d-e73c-4112-af7c-b68530f38222', 'User', '12/28', '0000');
insert into ecomm.cart values ('cacab31d-e73c-4112-af7c-b68530f38222', 'a1b9b31d-e73c-4112-af7c-b68530f38222');
insert into ecomm.cart values ('cacab31d-e73c-4112-af7c-b68530f38223', 'a1b9b31d-e73c-4112-af7c-b68530f38223');
insert into ecomm.item values('a7384042-e4aa-4c93-85ae-31a346dad702', '6d62d909-f957-430e-8689-b5129c0bb75e', 1, 17.15);
insert into ecomm.cart_item values ('cacab31d-e73c-4112-af7c-b68530f38222', 'a7384042-e4aa-4c93-85ae-31a346dad702');
insert into ecomm.item values('a7384042-e4aa-4c93-85ae-31a346dad703', 'd3588630-ad8e-49df-bbd7-3167f7efb246', 1, 10.99);
insert into ecomm.cart_item values ('cacab31d-e73c-4112-af7c-b68530f38222', 'a7384042-e4aa-4c93-85ae-31a346dad703');
insert into ecomm.orders(id, customer_id, address_id, card_id, order_date, total, payment_id, shipment_id, status) values ('0a59ba9f-629e-4445-8129-b9bce1985d6a','a1b9b31d-e73c-4112-af7c-b68530f38222', 'a731fda1-aaad-42ea-bdbc-a27eeebe2cc0', '618ffaff-cbcd-48d4-8848-a15601e6725b', current_timestamp, 38.14, NULL, NULL, 'CREATED');
INSERT INTO ecomm.item VALUES
  ('a7384042-e4aa-4c93-85ae-31a346dad704', '6d62d909-f957-430e-8689-b5129c0bb75e', 1, 17.15),
  ('a7384042-e4aa-4c93-85ae-31a346dad705', '3395a42e-2d88-40de-b95f-e00e1502085b', 1, 20.99);
INSERT INTO ecomm.order_item VALUES
  ('66682caa-a6d8-46ed-a173-ff822f754e1c', '0a59ba9f-629e-4445-8129-b9bce1985d6a', 'a7384042-e4aa-4c93-85ae-31a346dad704'),
  ('efeefa71-2760-412a-9ec8-0a040d90f02c', '0a59ba9f-629e-4445-8129-b9bce1985d6a', 'a7384042-e4aa-4c93-85ae-31a346dad705');