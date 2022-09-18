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
        String query = "insert into Dust(dustId, lat, lon) values (?,?,?) " +
                "on duplicate key " +
                "update lat = ?, lon = ? ";
        double latitude = dustLocationDto.getLatitude();
        double longitude = dustLocationDto.getLongitude();
        return this.jdbcTemplate.update(query, userId, latitude, longitude, latitude, longitude);
    }
}