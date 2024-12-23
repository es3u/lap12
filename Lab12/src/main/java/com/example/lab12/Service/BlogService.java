package com.example.lab12.Service;

import com.example.lab12.ApiResponse.ApiException;
import com.example.lab12.Model.Blog;
import com.example.lab12.Model.User;
import com.example.lab12.Respository.AuthRepository;
import com.example.lab12.Respository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogService {
    private final AuthRepository authRepository;
    private final BlogRepository blogRepository;

    public List<Blog> getMyBlogs(Integer UserId){
        User user = authRepository.findUserById(UserId);
        if (user == null){
            throw new ApiException("User not found");
        }
        return blogRepository.findAllByUser(user);
    }

    public void addBlog(Integer UserId , Blog blog){
        User user = authRepository.findUserById(UserId);
        if (user == null){
            return;
        }
        blog.setUser(user);
        blogRepository.save(blog);
    }

    public void updateBlog(Integer UserId ,Integer blogId, Blog blog){
        Blog blog1 = blogRepository.findBlogById(blogId);
        if (blog1 == null){
             throw  new ApiException("Blog not found");
        }
        if (!blog1.getUser().getId().equals(UserId)){
            throw new ApiException("blog not belong to you");
        }
        User user = authRepository.findUserById(UserId);
        if (user == null){
            throw new ApiException("User not found");
        }
        blog1.setUser(user);
        blog1.setTitle(blog.getTitle());
        blog1.setBody(blog.getBody());
        blogRepository.save(blog);
    }


    public void deleteBlog(Integer UserId ,Integer blogId){
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null){
            throw new ApiException("Blog not found");
        }
        if (!blog.getUser().getId().equals(UserId)){
            throw new ApiException("blog not belong to you");
        }
        User user = authRepository.findUserById(UserId);
        if (user == null){
            throw new ApiException("User not found");
        }
        blogRepository.delete(blog);
    }

    public Blog getBlogById(Integer userId , Integer blogId){
        Blog blog = blogRepository.findBlogById(blogId);
        if (blog == null){
            throw new ApiException("Blog not found");
        }
        if (!blog.getUser().getId().equals(userId)){
            throw new ApiException("blog not belong to you");
        }
        User user = authRepository.findUserById(userId);
        if (user == null){
            throw new ApiException("User not found");
        }

        return blog;
    }

    public Blog getBlogByTitle(Integer userId , String title){
        Blog blog = blogRepository.findBlogByTitle(title);
        if (blog == null){
            throw new ApiException("Blog not found");
        }
        User user = authRepository.findUserById(userId);
        if (!blog.getTitle().equals(title)){
            throw new ApiException("title not match");
        }
        if (!blog.getUser().getId().equals(userId)){
            throw new ApiException("blog not belong to you");
        }

        if (user == null){
            throw new ApiException("User not found");
        }
        return blog;
    }

}
