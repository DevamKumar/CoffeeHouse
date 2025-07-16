package com.coffeehouse.repository.custom;

import com.coffeehouse.dto.DailyOrderSummary;
import com.coffeehouse.model.Order;
import org.bson.Document;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Primary
@Repository
public class CustomOrderRepositoryImpl implements CustomOrderRepository{
    private final MongoOperations mongoOperations;

    public CustomOrderRepositoryImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    @Override
    public double calculateTotalRevenue() {
        Aggregation aggregation =  newAggregation(group().sum("totalPrice").as("totalRevenue"));
        AggregationResults<Document> result = mongoOperations.aggregate(aggregation, Order.class, Document.class);
        Document doc = result.getUniqueMappedResult();
        return doc != null ? doc.getDouble("totalRevenue") : 0.0;
    }

    @Override
    public List<DailyOrderSummary> getDailyOrderSummary() {
        Aggregation aggregation =  newAggregation(
                project()
                        .andExpression("{$dateToString: { format: '%Y-%m-%d', date: '$orderTime' }}").as("orderDate")
                        .and("totalPrice").as("totalPrice"),
                group("orderDate")
                        .count().as("totalOrders")
                        .sum("totalPrice").as("totalRevenue"),
                sort(Sort.Direction.DESC,"_id"),
                limit(7),
                project("totalOrders", "totalRevenue").and("_id").as("orderDate")
        );
        AggregationResults<Document> result = mongoOperations.aggregate(aggregation, Order.class, Document.class);
        return result.getMappedResults().stream().map(doc -> new DailyOrderSummary(
                doc.getString("orderDate"),
                doc.getInteger("totalOrders"),
                doc.getDouble("totalRevenue") // Corrected from "revenue" to "totalRevenue"
        )).collect(Collectors.toList());
    }
}


