package listeners;


import lombok.SneakyThrows;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@WebListener
public class dbIniter implements ServletContextListener {

    @Resource(name="jdbc/TestDB")
    private DataSource dataSource;

    @Override
    @SneakyThrows
    public void contextInitialized(ServletContextEvent sce) {

        Pattern pat=Pattern.compile("^\\d+\\.sql$");

        Path sqlDirPath=Paths.get(sce.getServletContext().getContextPath(),"/sql");;

        try(Connection connection=dataSource.getConnection();
            Statement statement=connection.createStatement();
            DirectoryStream<Path> paths=Files.newDirectoryStream(sqlDirPath)){
            for(Path filepath:sqlDirPath)
                if(pat.matcher(filepath.toFile().getName()).find()) {
                    statement.addBatch(
                            Files.lines(filepath)
                                .collect(Collectors.joining()));
                }
            statement.executeBatch(); //  this.getClass().getResourceAsStream()
        }

    }
}
