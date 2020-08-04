package app.uvsy.database;

import app.uvsy.database.exceptions.DBException;
import app.uvsy.database.exceptions.ItemDoesNotExistException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.amazonaws.services.dynamodbv2.model.ConditionalCheckFailedException;
import com.amazonaws.services.dynamodbv2.model.ConditionalOperator;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import com.universy.common.dynamo.DynamoDBMapperFactory;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class DynamoDBDAO<T> {
    private static DynamoDBMapper dynamoDBMapper;
    private final Class<T> entity;

    private DynamoDBDAO(Class<T> entity) {
        this.entity = entity;
    }

    private DynamoDBMapper getMapper() {
        dynamoDBMapper = Optional.ofNullable(dynamoDBMapper).orElseGet(DynamoDBMapperFactory::createMapper);
        return dynamoDBMapper;
    }

    public static <R> DynamoDBDAO<R> createFor(Class<R> clazz) {
        return new DynamoDBDAO<>(clazz);
    }

    public Optional<T> get(Object hashKey) {
        try {
            return Optional.ofNullable(getMapper().load(entity, hashKey));
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public Optional<T> get(Object hashKey, Object sortKey) {
        try {
            return Optional.ofNullable(getMapper().load(entity, hashKey, sortKey));
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public List<T> query(T object) {
        try {
            DynamoDBQueryExpression<T> query = new DynamoDBQueryExpression<T>().withHashKeyValues(object);
            return getMapper().query(entity, query);
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public List<T> query(T object, String index) {
        try {
            DynamoDBQueryExpression<T> query = new DynamoDBQueryExpression<T>()
                    .withHashKeyValues(object)
                    .withConsistentRead(Boolean.FALSE)
                    .withIndexName(index);
            return getMapper().query(entity, query);
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    /**
     * A query that operates under an index and with an specific map of filters.
     * The filters specified are performed only with the EQ/NE operator.
     */
    public List<T> query(T object, String index, Map<String, String> filters, boolean equal) {
        try {
            ComparisonOperator operator = equal ? ComparisonOperator.EQ : ComparisonOperator.NE;
            Map<String, Condition> queryFilters = filters.entrySet().stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> new Condition()
                                    .withComparisonOperator(operator)
                                    .withAttributeValueList(new AttributeValue().withS(e.getValue()))
                            )
                    );

            DynamoDBQueryExpression<T> query = new DynamoDBQueryExpression<T>()
                    .withHashKeyValues(object)
                    .withConsistentRead(Boolean.FALSE)
                    .withQueryFilter(queryFilters)
                    .withIndexName(index);
            return getMapper().query(entity, query);
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public List<T> query(T object, String index, Map<String, String> filters) {
        return this.query(object, index, filters, Boolean.TRUE);
    }


    public void save(T object) {
        try {
            getMapper().save(object);
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public void update(T object, Map<String, String> conditions) {
        try {
            DynamoDBSaveExpression saveExpression = new DynamoDBSaveExpression();
            Map<String, ExpectedAttributeValue> expectedAttributes = conditions
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            e -> new ExpectedAttributeValue(true)
                                    .withValue(new AttributeValue().withS(e.getValue()))
                    ));

            saveExpression.setExpected(expectedAttributes);
            saveExpression.setConditionalOperator(ConditionalOperator.AND);

            getMapper().save(object, saveExpression);
        } catch (ConditionalCheckFailedException e) {
            throw new ItemDoesNotExistException(e);
        } catch (Exception e) {
            throw new DBException(e);
        }
    }

    public void delete(T obj) {
        try {
            getMapper().delete(obj);
        } catch (Exception e) {
            throw new DBException(e);
        }
    }
}
