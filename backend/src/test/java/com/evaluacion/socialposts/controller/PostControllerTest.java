package com.evaluacion.socialposts.controller;

import com.evaluacion.socialposts.service.PostService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    private MockMvc mockMvc;    @MockBean
    private PostService postService;

    @Test
    public void shouldReturnAllPosts() throws Exception {
        when(postService.getAllPosts()).thenReturn(new ArrayList<>());
        
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnSearchResults() throws Exception {
        when(postService.searchPosts("test")).thenReturn(new ArrayList<>());
        
        mockMvc.perform(get("/api/posts/search?q=test"))
                .andExpect(status().isOk());
    }
}
