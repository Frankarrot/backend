package com.frankarrot.documentation;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class MemberControllerTest extends DocumentationTest {

    @Test
    void test() {
        docsGiven
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when().get("/api/members")
                .then().log().all()
                .apply(document("member"))
                .statusCode(HttpStatus.OK.value());
    }
}
