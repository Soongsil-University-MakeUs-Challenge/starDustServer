package ssumc.stardust.src.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ssumc.stardust.src.domain.CatchDto;

import javax.sql.DataSource;

@Repository
public class CatchRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 먼지 잡기
     */
    public int setCatch(int userIdByJwt, CatchDto dustNum){

        String query = "INSERT INTO Catch(userId, dustId) VALUES(?,?)";

        return this.jdbcTemplate.update(query, userIdByJwt, dustNum.getDustNum());
    }

    /**
     * 잡았던 먼지인지 체크
     */
    public int isCatch(int userIdByJwt, CatchDto dustNum){
        String query = "select exists (select userId from Catch where userId = ? and dustId = ?)";

        return this.jdbcTemplate.queryForObject(query, int.class, userIdByJwt, dustNum.getDustNum());
    }

    /**
     * 잡은 먼지 개수 확인
     */
    public int countCatch(int userIdByJwt, CatchDto dustNum){
        String query = "select count(*) from Catch where userId = ?";

        return this.jdbcTemplate.queryForObject(query, int.class, userIdByJwt);
    }
}
