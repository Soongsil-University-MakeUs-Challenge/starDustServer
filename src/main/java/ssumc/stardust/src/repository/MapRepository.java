package ssumc.stardust.src.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ssumc.stardust.src.domain.DustInfoDto;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class MapRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 먼지들 위치 정보 조회
     */
    public List<DustInfoDto> getDustInfo(int userId, String university) {

        String query = "select D.dustId as dustId, lat, lon, if(C.dustId > 0, TRUE, FALSE) as isCaught " +
                "from (select dustId, lat, lon from Dust join User on Dust.userId = User.userId where User.university = ?) D " +
                "left join (select dustId from Catch where userId = ?) C on D.dustId = C.dustId ";

        return jdbcTemplate.query(query, (rs, rowNum) -> new DustInfoDto(
                rs.getInt("dustId"),
                rs.getDouble("lon"),
                rs.getDouble("lat"),
                rs.getBoolean("isCaught")
        ), university, userId);
    }
}
