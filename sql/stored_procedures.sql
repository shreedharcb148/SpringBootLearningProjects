use schoolmanagement;

DELIMITER //

CREATE PROCEDURE GetAllCourses()
BEGIN
	select * from course
END

DELIMITER ;


