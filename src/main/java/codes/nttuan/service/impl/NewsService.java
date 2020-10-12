package codes.nttuan.service.impl;

import codes.nttuan.dao.INewsDAO;
import codes.nttuan.models.NewsModel;
import codes.nttuan.paging.Pageable;
import codes.nttuan.service.INewsService;
import codes.nttuan.sorting.Sortable;
import codes.nttuan.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.List;

public class NewsService implements INewsService {
    @Inject
    private INewsDAO newsDAO;
    @Override
    public List<NewsModel> findAll() {
        return newsDAO.findAll();
    }

    @Override
    public List<NewsModel> findAll(Pageable pageable) {
        return newsDAO.findAll(pageable);
    }

    @Override
    public List<NewsModel> findNewsByCategoryCode(HttpServletRequest req, HttpServletResponse res) {
        String[] pathParts = req.getPathInfo().split("/");
        return newsDAO.findByCategoryCode(pathParts[1]);
    }

    @Override
    public NewsModel findNewsById(HttpServletRequest req, HttpServletResponse res) {
        String path = req.getPathInfo();
        try {
            Long id = Long.parseLong(path.substring(path.lastIndexOf("-") + 1));
            return newsDAO.findOne(id);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public NewsModel save(NewsModel model) {
        Long id = newsDAO.save(model);
        return newsDAO.findOne(id);
    }

    @Override
    public NewsModel update(NewsModel model) {
        NewsModel old = newsDAO.findOne(model.getId());
        if(old != null){
            model.setCreatedDate(old.getCreatedDate());
            model.setCreatedBy(old.getCreatedBy());
            model.setModifiedDate(new Timestamp(System.currentTimeMillis()));
            newsDAO.update(model);
            return newsDAO.findOne(model.getId());
        } else{
            return null;
        }
    }

    @Override
    public void delete(long modelId) {
        // delete comment related
        newsDAO.delete(modelId);
    }

    @Override
    public void delete(long[] modelIds) {
        for(long id : modelIds){
            delete(id);
        }
    }

    @Override
    public int getTotalItems() {
        return newsDAO.getTotalItems();
    }

    @Override
    public NewsModel findOne(Long id) {
        return newsDAO.findOne(id);
    }
}
