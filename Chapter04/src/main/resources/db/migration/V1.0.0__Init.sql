create schema if not exists ecomm;

create TABLE IF NOT EXISTS ecomm.product (
	id uuid NOT NULL,
	name varchar(56) NOT NULL,
	description varchar(200),
	price numeric(16, 4) DEFAULT 0 NOT NULL,
	count numeric(8, 0),
	image_url varchar(40),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.tag (
	id uuid NOT NULL,
	name varchar(20),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.product_tag (
	product_id uuid NOT NULL,
	tag_id uuid NOT NULL,
	FOREIGN KEY (product_id)
		REFERENCES product(id),
	FOREIGN KEY(tag_id)
		REFERENCES tag(id)
);

insert into ecomm.product values ('6d62d909-f957-430e-8689-b5129c0bb75e', 'Antifragile', 'Antifragile - Things that gains from disorder. By Nassim Nicholas Taleb', 17.15, 33, '/catalogue/images/weave1.jpg');
insert into ecomm.product values ('a0a4f044-b040-410d-8ead-4de0446aec7e', 'Sapiens', 'Sapiens - A brief history of mankind. By Yuval Noah Harari', 7.99, 115, '/catalogue/images/bit_of_leg_1.jpeg');
insert into ecomm.product values ('808a2de1-1aaa-4c25-a9b9-6612e8f29a38', 'Thinking Fast and Slow', 'Thinking Fast and Slow. By winner of Nobel Prize - Danniel Kahneman', 17.32, 738, '/catalogue/images/cross_1.jpeg');
insert into ecomm.product values ('510a0d7e-8e83-4193-b483-e27e09ddc34d', 'How to Fail at Almost Everything and Still Win Big', 'How to Fail at Almost Everything and Still Win Big - Kind of Story of My Life. By Scott Adams', 15.00, 820, '/catalogue/images/puma_1.jpeg');
insert into ecomm.product values ('03fef6ac-1896-4ce8-bd69-b798f85c6e0b', 'Influence', 'Influence - Psychology of Persuasion. By Robert B. Cialdini Ph.D.', 99.99, 1, '/catalogue/images/holy_1.jpeg');
insert into ecomm.product values ('d3588630-ad8e-49df-bbd7-3167f7efb246', 'Poor Charlie''s Almanack', 'Poor Charlie''s Almanack - Wits and Wisdom of Charles T. Munger. By Peter Kauffman', 10.99, 801, '/catalogue/images/youtube_1.jpeg');
insert into ecomm.product values ('819e1fbf-8b7e-4f6d-811f-693534916a8b', 'Hackers & Painters', 'Hackers & Painters - Big Ideas from Computer Age. By Paul Graham', 14, 808, '/catalogue/images/WAT.jpg');
insert into ecomm.product values ('3395a42e-2d88-40de-b95f-e00e1502085b', 'Seeking Wisdom', 'Seeking Wisdom - From Darwin To Mungar. By Peter Bevelin',  12, 127, '/catalogue/images/classic.jpg');
insert into ecomm.product values ('3395a43e-2d88-40de-b95f-e00e1502085b', 'Thinking In Bets', 'Thinking In Bets - Making Smarter Decision. When you don''t have all the facts. By Annie Duke', 18, 438, '/catalogue/images/colourful_socks.jpg');
insert into ecomm.product values ('837ab141-399e-4c1f-9abc-bace40296bac', 'Zero to One', 'Zero to One - Notes on Startups, Or How to build future. Peter Theil', 15, 175, '/catalogue/images/catsocks.jpg');

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
	id uuid NOT NULL,
	username varchar(16),
	password varchar(40),
	first_name varchar(16),
	last_name varchar(16),
	email varchar(24),
	phone varchar(24),
	user_status varchar(16),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.address (
	id uuid NOT NULL,
	number varchar(24),
	residency varchar(24),
	street varchar(24),
	city varchar(24),
	state varchar(24),
	country varchar(24),
	pincode varchar(10),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.user_address (
	user_id uuid NOT NULL,
	address_id uuid NOT NULL,
	FOREIGN KEY (user_id)
		REFERENCES ecomm.user(id),
	FOREIGN KEY(address_id)
		REFERENCES ecomm.address(id)
);

create TABLE IF NOT EXISTS ecomm.payment (
	id uuid NOT NULL,
	authorized boolean,
	message varchar(64),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.card (
	id uuid NOT NULL,
	number varchar(16),
	user_id uuid NOT NULL,
	last_name varchar(16),
	expires varchar(5),
	ccv numeric(4,0),
	FOREIGN KEY(user_id)
		REFERENCES ecomm.user(id),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.shipment (
	id uuid NOT NULL,
	est_delivery_date timestamp,
	carrier varchar(24),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.orders (
	id uuid NOT NULL,
	customer_id uuid NOT NULL,
	address_id uuid NOT NULL,
	card_id uuid,
	orderDate timestamp,
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
	id uuid NOT NULL,
	product_id uuid NOT NULL,
	quantity numeric(8, 0),
	unitPrice numeric(16, 4) NOT NULL,
  FOREIGN KEY(product_id)
		REFERENCES ecomm.product(id),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.order_item (
	order_id uuid NOT NULL,
	item_id uuid NOT NULL,
	FOREIGN KEY (order_id)
		REFERENCES ecomm.orders(id),
	FOREIGN KEY(item_id)
		REFERENCES ecomm.item(id)
);

create TABLE IF NOT EXISTS ecomm.authorization (
  id uuid NOT NULL,
	order_id uuid NOT NULL,
	authorized boolean,
	time timestamp,
	message varchar(16),
	error varchar(24),
	FOREIGN KEY (order_id)
		REFERENCES ecomm.orders(id),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.cart (
  id uuid NOT NULL,
	user_id uuid NOT NULL,
	FOREIGN KEY (user_id)
		REFERENCES ecomm.user(id),
	PRIMARY KEY(id)
);

create TABLE IF NOT EXISTS ecomm.cart_item (
	cart_id uuid NOT NULL,
	item_id uuid NOT NULL,
	FOREIGN KEY (cart_id)
		REFERENCES ecomm.cart(id),
	FOREIGN KEY(item_id)
		REFERENCES ecomm.item(id)
);


