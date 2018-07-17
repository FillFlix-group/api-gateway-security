#11-June-2018

INSERT INTO `lms_users`.`permission` (`id`, `name`) VALUES ('46', 'APPROVE_SUBSCRIPTION');

INSERT INTO `lms_users`.`role_permission` (`role_id`, `permission_id`) VALUES ('1', '46');

UPDATE `lms_users`.`permission` SET `name`='VEHICLE_SEARCH' WHERE `id`='18';
