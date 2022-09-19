package ssumc.stardust.src.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ssumc.stardust.src.domain.DustLocationDto;

import javax.sql.DataSource;

@Repository
public class DustRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 먼지 위치 정보 업테이트
     */
    public int setLocation(int userId, DustLocationDto dustLocationDto) {
        String query = "update Dust set lat = ?, lon = ? where userId = ?";
        double latitude = dustLocationDto.getLatitude();
        double longitude = dustLocationDto.getLongitude();
        return this.jdbcTemplate.update(query, latitude, longitude, userId);
    }

    /**
     * 유저 역할 조회
     */
    public String checkUserRole(int userId) {
        String query = "select role from User where userId = ?";
        return this.jdbcTemplate.queryForObject(query, String.class, userId);
    }
}
