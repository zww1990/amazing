package com.amazing.service.impl;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.amazing.domain.TStudent;
import com.amazing.mapper.TStudentMapper;
import com.amazing.service.TStudentService;

@Service
@Transactional
public class TStudentServiceImpl implements TStudentService {
	private static final Logger log = LoggerFactory.getLogger(TStudentServiceImpl.class);
	@Resource
	private TStudentMapper studentMapper;

	@Override
	public TStudent findById(Integer id) {
		log.info("参数id={}", id);
		return this.studentMapper.selectByPrimaryKey(id);
	}
}
