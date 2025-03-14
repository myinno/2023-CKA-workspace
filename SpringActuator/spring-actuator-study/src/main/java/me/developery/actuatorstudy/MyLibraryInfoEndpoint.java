package me.developery.actuatorstudy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.actuate.endpoint.web.annotation.WebEndpoint;
import org.springframework.lang.Nullable;

import java.util.Arrays;
import java.util.List;


@Slf4j
@Endpoint(id = "myLibraryInfo")  // endpoint id 지정. 필수!
public class MyLibraryInfoEndpoint {

    /**
     * POST 방식으로 호출하는 예제 4.2
     */
    @WriteOperation
    public void changeSomething(String name, boolean enableSomething) {
        log.info("name: {}, enableSomething: {}", name, enableSomething);
    }

    /**
     * POST 방식으로 호출하는 예제 4.3
     * - 3 - path 파라미터 수신방법 (@Selector 방법)
     */
    @ReadOperation
    public String getPathVariable(@Selector String path1) {
        log.info("path1: {}", path1);
        return path1;
    }

    /**
     * POST 방식으로 호출하는 예제 4.3
     * - 3 - path 파라미터 수신방법 (@Selector 방법) --> path1/path2/path3
     */
    @ReadOperation
    public String getMultiPathVariable(@Selector(match = Selector.Match.ALL_REMAINING) String[] path) {
        log.info("path: {}", Arrays.asList(path));
        return Arrays.asList(path).toString();
    }


    //처음 작성된 메소드
//    @ReadOperation    // read 요청에 대한 메서드라는 의미
//    public List<LibraryInfo> getLibraryInfos() {
//        // TODO: 라이브러리 정보를 읽어서 name, version을 가져오는 코드가 있어야 하나 하드코딩으로 대체함.
//        LibraryInfo libraryInfo1 = new LibraryInfo();
//        libraryInfo1.setName("logback");
//        libraryInfo1.setVersion("1.0.0");
//
//        LibraryInfo libraryInfo2 = new LibraryInfo();
//        libraryInfo2.setName("jackson");
//        libraryInfo2.setVersion("2.0.0");
//
//        return Arrays.asList(libraryInfo1, libraryInfo2);
//    }

    /**
     * 2번쨰(오류 발생) - There was an unexpected error (type=Bad Request, status=400).
     * @param name
     * @param includeVersion
     * @return
     */
    @ReadOperation   // read 요청에 대한 메서드라는 의미
    public List<LibraryInfo> getLibraryInfos(@Nullable String name, boolean includeVersion) {
        LibraryInfo libraryInfo1 = new LibraryInfo();
        libraryInfo1.setName("logback");
        libraryInfo1.setVersion("1.0.0");

        LibraryInfo libraryInfo2 = new LibraryInfo();
        libraryInfo2.setName("jackson");
        libraryInfo2.setVersion("2.0.0");

        List<LibraryInfo> resultList = Arrays.asList(libraryInfo1, libraryInfo2);

        if (name != null) {
            resultList = resultList.stream()
                    .filter(libraryInfo -> {
                        return libraryInfo.getName().equals(name);
                    })
                    .toList();
        }
        if (!includeVersion) {
            resultList = resultList.stream()
                    .map(libraryInfo -> {
                        LibraryInfo simpleInfo = new LibraryInfo();
                        simpleInfo.setName(libraryInfo.getName());
                        // version 정보는 포함하지 않음.
                        return simpleInfo;
                    }).toList();
        }

        return resultList;
    }
}