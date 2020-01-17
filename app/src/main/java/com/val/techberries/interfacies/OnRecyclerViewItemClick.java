package com.val.techberries.interfacies;

import com.val.techberries.entities.Item;

public interface OnRecyclerViewItemClick<T> {
    void onClick(T item);
}
