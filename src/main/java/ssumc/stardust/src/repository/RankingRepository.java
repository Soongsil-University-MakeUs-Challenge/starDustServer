package ssumc.stardust.src.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ssumc.stardust.src.domain.RankerInfoDto;
import javax.sql.DataSource;
import java.util.List;

@Repository
public class RankingRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<RankerInfoDto> getRankerList(String code, int page, int size) {
        String query = "select nickname, right(phone, 4) as lastNum, TIMEDIFF(endTime, startTime) as playTime " +
                "from PlayTime inner join User U on U.userId = PlayTime.userId " +
                "where role = 'USER' and university = ? and status = 'ACTIVE' and PlayTime.endTime is not null " +
                "order by playTime limit ? offset ?";
        return jdbcTemplate.query(query,
                ((rs, rowNum) -> new RankerInfoDto(
                        rs.getString("nickname"),
                        rs.getString("lastNum"),
                        rs.getString("playTime")
                )), code, size, (page-1)*size);
    }
}
