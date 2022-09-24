package ssumc.stardust.src.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ssumc.stardust.src.domain.PostLoginReq;
import ssumc.stardust.src.domain.UserInfoDto;

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
    
     /**
     * 시간 중복 등록 조회
     */
    public int checkDuplication(int userId) {

        String query = "select exists (select userId from PlayTime where userId = ?)";
        return jdbcTemplate.queryForObject(query, int.class, userId);
    }

    /**
     * 회원가입
     */
    public int createUser(PostLoginReq postSignupReq){
        String query = "insert into User(nickname, phone, university) value (?, ?, ?)";
        Object[] params = new Object[]{postSignupReq.getNickname(), postSignupReq.getPhoneNum(), postSignupReq.getUnivCode()};
        this.jdbcTemplate.update(query, params);

        String lastInsertIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

    /**
     * 유저 exist 조회
     */
    public int existUser(PostLoginReq postLoginReq) {
        String query = "select exists(select userId from User where nickname = ? and phone = ? and university = ? and status='ACTIVE')";
        Object[] params = new Object[]{postLoginReq.getNickname(), postLoginReq.getPhoneNum(), postLoginReq.getUnivCode()};
        return jdbcTemplate.queryForObject(query, int.class, params);
    }

    /**
     * 유저 조회
     */
    public UserInfoDto getUser(PostLoginReq postLoginReq) {
        String query = "select userId, nickname, phone, role from User where nickname = ? and phone = ? and university = ? and status='ACTIVE'";
        return this.jdbcTemplate.queryForObject(query,
                (rs, rowNum) -> new UserInfoDto(
                        rs.getInt("userId"),
                        rs.getString("nickname"),
                        rs.getString("phone"),
                        rs.getString("role")
                ), postLoginReq.getNickname(), postLoginReq.getPhoneNum(), postLoginReq.getUnivCode());
    }
}
