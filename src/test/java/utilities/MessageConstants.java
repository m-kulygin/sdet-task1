package utilities;

/**
 * Utility class with message string constant values.
 *
 * @author Max Kulygin
 */
public class MessageConstants {
    /**
     * Substring that has to be contained by an alert for successful customer addition
     */
    public static final String MSG_CUSTOMER_ADD_SUCCESS_SUBSTRING = "Customer added successfully";
    /**
     * Message if an alert doesnt contain successful customer addition substring
     */
    public static final String MSG_CUSTOMER_ADD_ALERT_REQUIRES = "Alert message has to contain substring";
    /**
     * Message if customers list is not sorted ascending
     */
    public static final String MSG_CUSTOMERS_LIST_NOT_SORTED_ASC = "Customers list has to be sorted ascending";
    /**
     * Message if customers list is not sorted descending
     */
    public static final String MSG_CUSTOMERS_LIST_NOT_SORTED_DESC = "Customers list has to be sorted ascending";
    /**
     * Message if no name found to count average length
     */
    public static final String MSG_NO_CUSTOMER_AVG_NAME =
            "No suitable first name found to delete by length closest to average";
    /**
     * Message if customer with first name closest to average is not deleted
     */
    public static final String MSG_CUSTOMER_AVG_NAME_NOT_DELETED
            = "Customer with first name closest to average has to be deleted";
    /**
     * Message if customers list is empty
     */
    public static final String MSG_EMPTY_CUSTOMERS = "No data to calculate average first names length";
}
