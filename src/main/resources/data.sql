INSERT INTO public.system_manager(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password)
	VALUES (-1, 'SYSTEM_MANAGER', 'a', true, 'a', 'a', '$2a$10$8JKHCMhe4iYrf4TDtxqOyuh26q/hxI3p3lsz9gVxkgxKfFEEV56YK');

INSERT INTO public.restaurant(
	restaurant_id, restaurant_desc, restaurant_name)
	VALUES (0, 'r', 'r');

INSERT INTO public.restaurant(
	restaurant_id, restaurant_desc, restaurant_name)
	VALUES (-1, 'r1', 'r1');

INSERT INTO public.restaurant_manager(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password, restaurant_restaurant_id)
	VALUES (-2, 'RESTAURANT_MANAGER', 'f', true, 'f', 'f', '$2a$04$xk0n6OOy8k0lzKIPei1YQOXF/nWrvm0ri.WX4h1McB6i9LmyiSoVa', 0);

INSERT INTO public.restaurant_manager(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password, restaurant_restaurant_id)
	VALUES (-22, 'RESTAURANT_MANAGER', 'ff', true, 'ff', 'ff', '$2a$10$i4AgN9RCl4f5mdSUwd/4z.L9xPgFrt.uz9FHAnCIRwMBICf/f4fzG', -1);

INSERT INTO public.bartender(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password, restaurant_restaurant_id)
	VALUES (-3, 'BARTENDER', 'b', true, 'b', 'b', '$2a$04$u.lkV0MiMXqkscmk8Ful6OBNDTNY9DsO8cSrcF/nqKMOdSHyl6twG', 0);

INSERT INTO public.waiter(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password, restaurant_restaurant_id)
	VALUES (-4, 'WAITER', 'w', true, 'w', 'w', '$2a$04$DWujWEfVKvrYvLjoJAYpi.bJkdXZj9/SuiZFmiQuuqkOQei3KqMSu', 0);

INSERT INTO public.provider(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password)
	VALUES (-5, 'PROVIDER', 'e', true, 'e', 'e', '$2a$10$pC9iHlIFaBi4TF59lEgt6.QlD4pK8fXvtqDb18o/wN3KExtFaSAri');

INSERT INTO public.provider(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password)
	VALUES (-6, 'PROVIDER', 'e1', true, 'e1', 'e1', '$2a$10$0I7lCbcA2xZEvFYC32rI5Os.4iVQqHII1Vltijr9hJf86MiAse5HG');

INSERT INTO public.shopping_list(
	shopping_list_id, deadline, shopping_list_name, accepted_offer_id, restaurant_restaurant_id)
	VALUES (0, '2017-07-20 10:00:00', 'Sarma', null, 0);

INSERT INTO public.shopping_item(
	id, shopping_item_amount, shopping_item_name, shopping_list_shopping_list_id)
	VALUES (0, '60', 'cabbage', 0);

INSERT INTO public.shopping_item(
	id, shopping_item_amount, shopping_item_name, shopping_list_shopping_list_id)
	VALUES (-1, '200kg', 'meat', 0);

INSERT INTO public.shopping_item(
	id, shopping_item_amount, shopping_item_name, shopping_list_shopping_list_id)
	VALUES (-2, '150kg', 'rice', 0);

INSERT INTO public.shopping_list(
	shopping_list_id, deadline, shopping_list_name, accepted_offer_id, restaurant_restaurant_id)
	VALUES (-1, '2017-07-25 10:00:00', 'Pancakes', null, 0);

INSERT INTO public.shopping_item(
	id, shopping_item_amount, shopping_item_name, shopping_list_shopping_list_id)
	VALUES (-3, '10kg', 'butter', -1);

INSERT INTO public.shopping_item(
	id, shopping_item_amount, shopping_item_name, shopping_list_shopping_list_id)
	VALUES (-4, '200kg', 'flour', -1);

INSERT INTO public.shopping_item(
	id, shopping_item_amount, shopping_item_name, shopping_list_shopping_list_id)
	VALUES (-5, '100kg', 'sugar', -1);

INSERT INTO public.shopping_item(
	id, shopping_item_amount, shopping_item_name, shopping_list_shopping_list_id)
	VALUES (-6, '250', 'eggs', -1);

INSERT INTO public.shopping_item(
	id, shopping_item_amount, shopping_item_name, shopping_list_shopping_list_id)
	VALUES (-7, '100l', 'milk', -1);

INSERT INTO public.offer(
	id, amount, version, provider_user_id, shopping_list_shopping_list_id)
	VALUES (0, 100, 0, -5, 0);

INSERT INTO public.offer(
	id, amount, version, provider_user_id, shopping_list_shopping_list_id)
	VALUES (-1, 500, 1, -6, 0);

INSERT INTO public.dish_type(
	dish_type_id, dish_type_name, dish_type_restaurant_id)
	VALUES (0, 'meat', 0);

INSERT INTO public.dish_type(
	dish_type_id, dish_type_name, dish_type_restaurant_id)
	VALUES (-1, 'salad', 0);

INSERT INTO public.dish_type(
	dish_type_id, dish_type_name, dish_type_restaurant_id)
	VALUES (-2, 'fish', 0);
