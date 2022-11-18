package Basic_Spring.demo.web;

import Basic_Spring.demo.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {

    private final ObjectProvider<MyLogger> myLoggerProvider;


    public void logic(String id) {
        MyLogger logger = myLoggerProvider.getObject();
        logger.log("service id = " + id);
    }
}
