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




-- Guests

INSERT INTO public.guest(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password)
	VALUES (-10, 'GUEST', 'gg1_ne', false, 'g1_ne', 'g1_ne', '$2a$04$KO4zt5Smt4cHXokLgUt2T.wmcwsHPLICQ3koBK0aOIMdAAzf3oN6S');

INSERT INTO public.guest(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password)
	VALUES (-11, 'GUEST', 'gg2', true, 'g2', 'g2', '$2a$04$Zz//MPSP4/jqHLDyF2bc6ex8nOwuVb834b0onXiXeaqoURi3r7nnq');

INSERT INTO public.guest(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password)
	VALUES (-12, 'GUEST', 'gg3', true, 'g3', 'g3', '$2a$04$ghLb9TSD0OysRCRLhMgZVuLqfq.9z8HVDRMh1ND6h6A0xr9bCnYZe');

INSERT INTO public.guest(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password)
	VALUES (-13, 'GUEST', 'gg4', true, 'g4', 'g4', '$2a$04$u8zezfPQnEDmXQTXlhb5auaJGZtJyTevUwP7iqbt/q.nwCKSmfUAK');


-- Friendships

INSERT INTO public.friendship(
	friendship_id, friendship_status, friendship_last_action_user, friendship_first_user, friendship_second_user)
	VALUES (-10, 'ACCEPTED', -11, -11, -12);

INSERT INTO public.friendship(
	friendship_id, friendship_status, friendship_last_action_user, friendship_first_user, friendship_second_user)
	VALUES (-11, 'PENDING', -11, -11, -13);

INSERT INTO public.friendship(
	friendship_id, friendship_status, friendship_last_action_user, friendship_first_user, friendship_second_user)
	VALUES (-12, 'PENDING', -12, -12, -13);


-- Reservations and Invitations

INSERT INTO public.reservation(
	reservation_id, reservation_end_time, reservation_start_time, reservation_status, reservation_version, reservation_reserver_id, reservation_restaurant_id)
	VALUES (-10, '2018-06-20 09:15:00', '2018-06-20 10:15:00', 'FINISHED', 0, -11, 0);

INSERT INTO public.reservation(
	reservation_id, reservation_end_time, reservation_start_time, reservation_status, reservation_version, reservation_reserver_id, reservation_restaurant_id)
	VALUES (-11, '2018-06-20 10:15:00', '2018-06-20 11:15:00', 'SENT', 0, -11, 0);

INSERT INTO public.invitation(
	invitation_id, invitation_status, invitation_invited_user_id, invitation_reservation_id)
	VALUES (-10, 'PENDING', -12, -10);

INSERT INTO public.invitation(
	invitation_id, invitation_status, invitation_invited_user_id, invitation_reservation_id)
	VALUES (-11, 'ACCEPTED', -12, -10);

INSERT INTO public.invitation(
	invitation_id, invitation_status, invitation_invited_user_id, invitation_reservation_id)
	VALUES (-12, 'ACCEPTED', -12, -11);


-- Verification token

INSERT INTO public.verification_token(
	verification_token_id, verification_token_expiry_date, verification_token_purpose, verification_token_string, verification_token_user_id)
	VALUES (-10, '2018-06-20 09:15:00', 'REGISTRATION', 'registrationTokenSample', -10);

INSERT INTO public.verification_token(
	verification_token_id, verification_token_expiry_date, verification_token_purpose, verification_token_string, verification_token_user_id)
	VALUES (-11, '2018-06-20 09:15:00', 'INVITATION', 'invitationTokenSample', -12);

