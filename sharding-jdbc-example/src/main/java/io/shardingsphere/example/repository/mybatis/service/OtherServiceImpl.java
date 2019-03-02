package io.shardingsphere.example.repository.mybatis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.shardingsphere.example.repository.api.entity.OtherItem;
import io.shardingsphere.example.repository.api.repository.OtherItemRepository;
import io.shardingsphere.example.repository.api.trace.MemoryLogService;

@Service
public class OtherServiceImpl implements OtherService {
	
	@Autowired
	private OtherItemRepository otherItemRepository;

	@Override
	public void initEnvironment() {
		otherItemRepository.createTableIfNotExists();
		otherItemRepository.truncateTable();

	}

	@Override
	public void cleanEnvironment() {
		otherItemRepository.dropTable();

	}

	@Override
	public void processSuccess() {
		System.out.println("-------------- Process Success Begin ---------------");
        List<Long> otherIds = insertData();
        printData();
        deleteData(otherIds);
        printData();
        System.out.println("-------------- Process Success Finish --------------");

	}

	private void deleteData(List<Long> otherIds) {
		for(Long id:otherIds) {
			otherItemRepository.delete(id);
		}
		
	}

	private List<Long> insertData() {
		System.out.println("---------------------------- Insert Data ----------------------------");
        List<Long> result = new ArrayList<>(10);
        for (int i = 1; i <= 10; i++) {
            OtherItem other = new OtherItem();
            other.setUserId(i);
            other.setOrderId(ThreadLocalRandom.current().nextInt(1000));
            other.setOrderItemId(ThreadLocalRandom.current().nextInt(1000));
            otherItemRepository.insert(other);
            result.add(other.getOrderId());
        }
        return result;
	}

	@Override
	public void processFailure() {

	}

	@Override
	public void printData() {
		System.out.println("---------------------------- Print Other Data -----------------------");
        for (OtherItem each : otherItemRepository.selectAll()) {
            System.out.println(each);
        }
	}

	@Override
	public MemoryLogService getMemoryLogService() {
		return null;
	}

}
