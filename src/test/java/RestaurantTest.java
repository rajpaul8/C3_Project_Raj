import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void creating_restaurant_object_before_each_test(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }
    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        Restaurant restaurant1 = Mockito.spy(restaurant);
        LocalTime setCurrentTime = LocalTime.parse("12:30:00");
        Mockito.when(restaurant1.getCurrentTime()).thenReturn(setCurrentTime);
        assertTrue(restaurant1.isRestaurantOpen());
    }
    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant restaurant1 = Mockito.spy(restaurant);
        LocalTime setCurrentTime = LocalTime.parse("01:30:00");
        Mockito.when(restaurant1.getCurrentTime()).thenReturn(setCurrentTime);
        assertFalse(restaurant1.isRestaurantOpen());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    @Test
    public void get_the_name_of_restaurant_(){
        assertEquals("Amelie's cafe",restaurant.getName());
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    // Calculating the Cost of the selected Items
    @Test
    public void calculated_cost_of_the_selected_list_of_items_from_the_menu_equals_to_388_which_is_the_sum_of_SelectedItems(){
        // Adding Selected Items from menu to selection list
        int initialSelectionSize = restaurant.selectedMenu().size();
        restaurant.IncludeSelectItem("Sweet corn soup");
        restaurant.IncludeSelectItem("Vegetable lasagne");
        assertEquals(initialSelectionSize+2,restaurant.selectedMenu().size());
        //Removing From the selection List
        int SelectionSize = restaurant.selectedMenu().size();
        restaurant.RemoveSelectItem("Sweet corn soup");
        assertEquals(SelectionSize-1,restaurant.selectedMenu().size());
        restaurant.IncludeSelectItem("Sweet corn soup");
        assertEquals(2,restaurant.selectedMenu().size());
        //Get The sum total price of the selected List
        assertEquals(388,restaurant.displayOrderTotal());
    }
}