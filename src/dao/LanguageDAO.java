package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Languages;

public class LanguageDAO extends DAO {
    public List<Languages> all() throws Exception {
        Connection connection = getConnection();
        List<Languages> languages = new ArrayList<Languages>();

        PreparedStatement pStatement = connection.prepareStatement("select * from languages ORDER BY code");

        ResultSet rSet = pStatement.executeQuery();

        while (rSet.next()) {
            Languages language = new Languages();

            language.setId(rSet.getInt("id"));
            language.setName(rSet.getString("name"));
            language.setCode(rSet.getString("code"));

            languages.add(language);
        }

        pStatement.close();
        connection.close();

        return languages;
    }
}
