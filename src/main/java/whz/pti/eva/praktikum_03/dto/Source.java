package whz.pti.eva.praktikum_03.dto;

import lombok.Getter;
import lombok.Setter;
import whz.pti.eva.praktikum_03.domain.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class Source {

    private int quantity;
    private Map<String, Item> orderedItemList = new HashMap<>();

    public Map<String, Item> getOrderedItemList() {
        return orderedItemList;
    }

    public void setOrderedItemList(Map<String, Item> orderedItemList) {
        this.orderedItemList = orderedItemList;
    }
}