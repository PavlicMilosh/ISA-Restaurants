INSERT INTO public.system_manager(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password)
	VALUES (-1, 'SYSTEM_MANAGER', 'a', true, 'a', 'a', '$2a$10$8JKHCMhe4iYrf4TDtxqOyuh26q/hxI3p3lsz9gVxkgxKfFEEV56YK');

INSERT INTO public.restaurant(
	restaurant_id, restaurant_desc, restaurant_name)
	VALUES (0, 'r', 'r');

INSERT INTO public.restaurant_manager(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password, restaurant_restaurant_id)
	VALUES (-2, 'RESTAURANT_MANAGER', 'f', true, 'f', 'f', '$2a$04$xk0n6OOy8k0lzKIPei1YQOXF/nWrvm0ri.WX4h1McB6i9LmyiSoVa', 0);

INSERT INTO public.bartender(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password, restaurant_restaurant_id)
	VALUES (-3, 'BARTENDER', 'b', true, 'b', 'b', '$2a$04$u.lkV0MiMXqkscmk8Ful6OBNDTNY9DsO8cSrcF/nqKMOdSHyl6twG', 0);

INSERT INTO public.waiter(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password, restaurant_restaurant_id)
	VALUES (-4, 'WAITER', 'w', true, 'w', 'w', '$2a$04$DWujWEfVKvrYvLjoJAYpi.bJkdXZj9/SuiZFmiQuuqkOQei3KqMSu', 0);