# TypeDecoration

Project `TypeDecoration` is a powerful ItemDecoration extension for RecyclerView, it providers a multi types for ItemDecoration

## Usage

```java

        Decorator decorator = Decorator.newBuilder(Decorator.VERTICAL)
                .condition(new MultiTypeCondition())
                .decoration(decoration)
                .withDrawOverlay(true)
                .withDrawEnd(true)
                .ifType(DelegateAdapterCompat.encodeViewType(0, index$1))
                .then()
                .ifType(DelegateAdapterCompat.encodeViewType(0, index$2))
                .thenDrawable(DecorationUtils.listDivider(this))
                .ifType(
                        DelegateAdapterCompat.encodeViewType(0, index$3),
                        DelegateAdapterCompat.encodeViewType(0, index$5)
                )
                .thenDrawable(ContextCompat.getDrawable(this, R.drawable.divider_vertical2))
                .build();


        recyclerView.addItemDecoration(decorator);
```


## Feature

### vlayout-VirtualLayoutManager

- [x] LinearLayoutHelper
- [ ] OnePlusNLayoutHelper
- [ ] StaggeredGridLayoutHelper
- [ ] GridLayoutHelper
- [ ] ....


### RecylcerView-LayoutManager

- [x] LinearLayoutManager
- [ ] GridLayoutManager
- [ ] StaggeredGridLayoutManager
- [ ] ...
