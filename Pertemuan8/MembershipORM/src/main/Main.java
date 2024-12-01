package Pertemuan8.src.main;

import Pertemuan8.MembershipORM.src.dao.JenisMemberDao;
import Pertemuan8.MembershipORM.src.dao.MemberDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import Pertemuan8.MembershipORM.src.view.main.MainFrame;

import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        JenisMemberDao jenisMemberDao = new JenisMemberDao(sqlSessionFactory);
        MemberDao memberDao = new MemberDao(sqlSessionFactory);
        MainFrame f = new MainFrame(jenisMemberDao, memberDao);

        javax.swing.SwingUtilities.invokeLater(() -> {
            f.setVisible(true);
        });
    }
}
