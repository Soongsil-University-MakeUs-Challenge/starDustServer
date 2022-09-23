package ssumc.stardust.src.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * 게임 시작 시간 업데이트
     */
    public int insertStartTime(int userId) {

        String query = "insert into PlayTime(userId) values (?)";
        return jdbcTemplate.update(query, userId);
    }

    /**
     * 유저 역할 조회
     * USER 역할인 사람만 허용
     */
    public int checkUserRole(int userId) {

        String query = "select exists (select userId from User where userId = ? and role = 'USER')";
        return jdbcTemplate.queryForObject(query, int.class, userId);
    }

}
