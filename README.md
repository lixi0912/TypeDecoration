# TypeDecoration

Project `TypeDecoration` is a powerful ItemDecoration extension for RecyclerView, it providers a multi types for ItemDecoration

## Usage

```java
     Decorator decorator = Decorator.newBuilder(Decorator.VERTICAL)
                .condition(new MultiTypeCondition())
                .decoration(decoration)
                .withDrawOverlay(true)
                .withDrawEnd(true)
                .withType(DelegateAdapterCompat.encodeViewType(0, index$1))
                .withType(DelegateAdapterCompat.encodeViewType(0, index$2))
                .withType(
                        DelegateAdapterCompat.encodeViewType(0, index$3),
                        DelegateAdapterCompat.encodeViewType(0, index$5)
                )
                .then()
                .thenDrawable(DelegateAdapterCompat.encodeViewType(0, index$2), DecorationUtils.listDivider(this))
                .thenDrawable(DelegateAdapterCompat.encodeViewType(0, index$5), ContextCompat.getDrawable(this, R.drawable.divider_vertical2))
                .end()
                .build();

        recyclerView.addItemDecoration(decorator);
```


## Feature

### vlayout-VirtualLayoutManager

- [ ] OnePlusNLayoutHelper
- [ ] StaggeredGridLayoutHelper
- [ ] GridLayoutHelper
- [ ] ....


### RecylcerView-LayoutManager

- [ ] GridLayoutManager
- [ ] StaggeredGridLayoutManager
- [ ] ...
