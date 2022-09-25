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

    public List<RankerInfoDto> getRankerList(int page, int size) {
        String query = "select nickname, right(phone, 4) as lastNum, TIMEDIFF(endTime, startTime) as playTime from PlayTime join User U on U.userId = PlayTime.userId\n" +
                "where role = 'USER' and status = 'ACTIVE'\n" +
                "order by playTime" +
                "limit ? offset ?";
        return jdbcTemplate.query(query,
                ((rs, rowNum) -> new RankerInfoDto(
                        rs.getString("nickname"),
                        rs.getString("lastNum"),
                        rs.getString("playTime")
                )), size, (page-1)*size);
    }
}
