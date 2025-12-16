package com.ikeda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ikeda.entity.DvdItem;
import com.ikeda.repository.DvdItemRepository;

@Service
public class ItemService {

    @Autowired
    private DvdItemRepository dvdItemRepository;

    public Page<DvdItem> findAll(int page) {

        int pageSize = 9;
        Pageable pageable =
                PageRequest.of(page, pageSize, Sort.by("id").ascending());

        return dvdItemRepository.findAll(pageable);
    }
 // ★ 追加：IDで1件取得
    public DvdItem findById(Integer id) {
        return dvdItemRepository.findById(id).orElse(null);
    }
}

