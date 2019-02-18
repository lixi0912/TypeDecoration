# TypeDecoration

Project `TypeDecoration` is a powerful ItemDecoration extension for RecyclerView, it providers a multi types for ItemDecoration

## Usage

1. Simple way
```java
    Decorator decorator = DecoratorFactory
                      .newBuilder()
                      .simple()
                      .thenDecoration(new GridDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2), SPAN_COUNT))
                      .andMarginStart(marginStart)
                      .andMarginEnd(marginEnd)
                      .andDrawEnd(drawEnd)
                      .end();

    recyclerView.addItemDecoration(decorator);
```

2. Complicated way
```java

    Decorator decorator = DecoratorFactory.newBuilder()
                     .multi(
                             // GridLayoutHelper
                             DelegateAdapterCompat.encodeViewType(0, 4),
                             DelegateAdapterCompat.encodeViewType(0, 1),
                             // StaggeredGridLayoutHelper
                             DelegateAdapterCompat.encodeViewType(0, 6)
                     )
                     .thenDecoration(new GridDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2), SPAN_COUNT))
                     .andMarginEnd(marginEnd)
                     .andMarginStart(marginStart)
                     .andDrawEnd(drawEnd)
                     .ifType(
                             // OnePlusNLayoutHelper
                             DelegateAdapterCompat.encodeViewType(0, 7)
                     )
                     .thenDecoration(new OnePlusNDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical), 2))
                     .andMarginEnd(marginEnd)
                     .andMarginStart(marginStart)
                     .andDrawEnd(drawEnd)
                     .ifType(
                             // LinearLayoutHelper
                             DelegateAdapterCompat.encodeViewType(0, 0),
                             DelegateAdapterCompat.encodeViewType(0, 2),
                             DelegateAdapterCompat.encodeViewType(0, 3),
                             DelegateAdapterCompat.encodeViewType(0, 5)
                     )
                     .thenDecoration(new LinearDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2)))
                     .andMarginEnd(marginEnd)
                     .andMarginStart(marginStart)
                     .andDrawEnd(drawEnd)
                     .end()
      
    recyclerView.addItemDecoration(decorator);
```

## Issue

- [x] When using GridLayout or StaggeredGridLayout, TypeDecoration cannot handle the last raw margin space ( marginStart and marginEnd )


## Feature

### vlayout-VirtualLayoutManager

- [x] LinearLayoutHelper
- [x] OnePlusNLayoutHelper
- [x] StaggeredGridLayoutHelper
- [x] GridLayoutHelper
- [ ] ....


### RecylcerView-LayoutManager

- [x] LinearLayoutManager
- [x] GridLayoutManager
- [x] StaggeredGridLayoutManager
- [ ] ...
