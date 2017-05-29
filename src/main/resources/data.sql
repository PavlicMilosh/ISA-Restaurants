INSERT INTO public.system_manager(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password)
	VALUES (-1, 'SYSTEM_MANAGER', 'a', true, 'a', 'a', '$2a$10$8JKHCMhe4iYrf4TDtxqOyuh26q/hxI3p3lsz9gVxkgxKfFEEV56YK');

INSERT INTO public.restaurant(
	restaurant_id, restaurant_desc, restaurant_name)
	VALUES (0, 'r', 'r');

INSERT INTO public.restaurant_manager(
	user_id, user_authorities, user_email, user_enabled, user_first_name, user_last_name, user_password, restaurant_restaurant_id)
	VALUES (-2, 'RESTAURANT_MANAGER', 'f', 'f', 'f', 'f', 'f', 0);