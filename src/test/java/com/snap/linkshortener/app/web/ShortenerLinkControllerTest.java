package com.snap.linkshortener.app.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.snap.linkshortener.app.web.model.ShorterLink;
import org.apache.catalina.User;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.apache.catalina.users.MemoryUserDatabase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = {ShortenerLinkController.class})
@ExtendWith(SpringExtension.class)
class ShortenerLinkControllerTest {

    @Autowired
    private ShortenerLinkController shortenerLinkController;

    /**
     * Method under test: {@link ShortenerLinkController#createShorterLink(ShorterLink)}
     */
    @Test
    void testCreateShorterLink() throws Exception {
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders.post("/")
                .contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ShorterLink()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(shortenerLinkController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link ShortenerLinkController#createShorterLink(ShorterLink)}
     */
    @Test
    void testCreateShorterLink2() throws Exception {
        User user = mock(User.class);
        when(user.getName()).thenReturn("Name");
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/");
        postResult.principal(new UserDatabaseRealm.UserDatabasePrincipal(user, new MemoryUserDatabase()));
        MockHttpServletRequestBuilder contentTypeResult = postResult.contentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content(objectMapper.writeValueAsString(new ShorterLink()));
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(shortenerLinkController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
    }

    /**
     * Method under test: {@link ShortenerLinkController#getOriginalLink(String)}
     */
    @Test
    void testGetOriginalLink() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(shortenerLinkController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(500));
    }
}

