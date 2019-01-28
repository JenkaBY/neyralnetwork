package by.jenka.ann.collection;

import java.util.ArrayList;

public class SafeArrayList<E> extends ArrayList<E>
{
    @Override
    public E get(int index)
    {
        if (!isIndexInSize(index)) {
            return null;
        }
        return super.get(index);
    }

    @Override
    public E set(int index, E element)
    {
        if (!isIndexInSize(index)) {
            int i = this.size() > 0 ? this.size() - 1 : 0;
            for (; i <= index; i++) {
                super.set(i, null);
            }
        }
        return super.set(index, element);
    }

    private boolean isIndexInSize(int index)
    {
        return index >= this.size();
    }
}
