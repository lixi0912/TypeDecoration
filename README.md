# TypeDecoration

Project `TypeDecoration` is a powerful ItemDecoration extension for RecyclerView, it providers a multi types for ItemDecoration

## Usage

1. Simple way
```java
     Decorator decorator = Decorator.newBuilder()
                .noCondition()
                .decoration(new GridDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2)))
                .withDrawOverlay(true)// call draw or drawOver,default is false 
                .end();

        recyclerView.addItemDecoration(decorator);
```

2. Complicated way
```java

         MultiTypeDecoration decoration = new MultiTypeDecoration();
              int margin = getResources().getDimensionPixelSize(R.dimen.margin);
      
              Decorator decorator = Decorator.newBuilder()
                      .condition(new MultiTypeCondition())
                      .decoration(decoration)
                      .withDrawOverlay(true)
                      .ifType(
                              DelegateAdapterCompat.encodeViewType(0, index$4),
                              DelegateAdapterCompat.encodeViewType(0, index$6),
                              DelegateAdapterCompat.encodeViewType(0, index$7)
                      )
                      .thenDecoration(new GridDecoration(ContextCompat.getDrawable(this, R.drawable.divider_vertical2)))
                      .andMarginEnd(margin)
                      .andMarginStart(margin)
                      .andDrawEnd(true)
                      .ifType(DelegateAdapterCompat.encodeViewType(0, index$0))
                      .thenDrawable(ContextCompat.getDrawable(this, R.drawable.divider_vertical2))
                      .andMarginStart(margin)
                      .andMarginEnd(margin)
                      .ifType(DelegateAdapterCompat.encodeViewType(0, index$1))
                      .thenNothing()
                      .ifType(DelegateAdapterCompat.encodeViewType(0, index$2))
                      .thenDrawable(DecorationUtils.listDivider(this))
                      .ifType(
                              DelegateAdapterCompat.encodeViewType(0, index$3),
                              DelegateAdapterCompat.encodeViewType(0, index$5)
                      )
                      .thenDrawable(ContextCompat.getDrawable(this, R.drawable.divider_vertical2))
                      .andDrawEnd(true)
                      .andMarginStart(margin)
                      .andMarginEnd(margin)
                      .end();
      
              recyclerView.addItemDecoration(decorator);
```

## Issue

- [ ] When using GridLayout or StaggeredGridLayout, TypeDecoration cannot handle the last raw margin space ( marginStart and marginEnd )


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
