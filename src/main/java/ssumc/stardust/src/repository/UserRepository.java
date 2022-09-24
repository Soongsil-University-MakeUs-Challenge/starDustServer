package ssumc.stardust.src.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ssumc.stardust.src.domain.PostSignUpReq;

import javax.sql.DataSource;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    public int checkNickname(String nickname){
//        String query = "select exists(select userId from User where nickname = ? and status='ACTIVE')";
//        return this.jdbcTemplate.queryForObject(query, int.class, nickname);
//    }
    /**
     * 회원가입
     */
    public int createUser(PostSignUpReq postSignupReq){
        String query = "insert into User(nickname, phone, university) value (?, ?, ?)";
        Object[] params = new Object[]{postSignupReq.getNickname(), postSignupReq.getPhoneNum(), postSignupReq.getUnivCode()};
        this.jdbcTemplate.update(query, params);

        String lastInsertIdQuery = "select last_insert_id()";
        System.out.println("last_insert_id: " + this.jdbcTemplate.queryForObject((lastInsertIdQuery), int.class));
        return this.jdbcTemplate.queryForObject(lastInsertIdQuery, int.class);
    }

}
