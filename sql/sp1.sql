delimiter $$
create procedure getCourses(in cid int,out cprize int)
begin
	-- cursor to iterate
    declare c_prize int;
    
    select cprize into c_prize
    from course 
    where cid = cid;
end

delimiter ;

call getCourses(1,@level);
select @level;