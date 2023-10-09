package com.husky.hqMovie.mapper;

import com.husky.hqMovie.pojo.Ticket;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketMapper {
    @Select("select * from tickets")
    @Results(id = "BaseResultMapper",value = {
            @Result(property = "name",column = "name"),
            @Result(property = "id",column = "id"),
            @Result(property = "price",column = "price"),
            @Result(property = "duration",column = "duration"),
            @Result(property = "room",column = "room"),
            @Result(property = "startDate",column = "startDate"),
            @Result(property = "startTime",column = "startTime")
    })
    List<Ticket> selectAllTicket();
    @Select("SELECT * FROM tickets WHERE name LIKE CONCAT('%', #{str}, '%')")
    List<Ticket> selectTicketByStr(String str);
    @Delete("delete from tickets where id=#{id}")
    Integer deleteTicketById(Integer id);
    @Update("update tickets set name=#{name},price=#{price},duration=#{duration},room=#{romm},startTime=#{startTime},startDate=#{startDate} where id=#{id}")
    Integer updateTicket(Ticket ticket);
    @Insert("insert into tickets values (null,#{name},#{price},#{duration},#{startDate},#{startTime},#{room})")
    Integer insetTicket(Ticket ticket);
}
