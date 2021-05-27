import * as echarts from 'echarts/core';
import {
    BarChart,
    // 系列类型的定义后缀都为 SeriesOption
    BarSeriesOption,
    LineChart,
    LineSeriesOption,
    PieChart,
    PieSeriesOption
} from 'echarts/charts';
import {
    TitleComponent,
    // 组件类型的定义后缀都为 ComponentOption
    TitleComponentOption,
    TooltipComponent,
    TooltipComponentOption,
    GridComponent,
    GridComponentOption,
    LegendComponent,
    LegendComponentOption,
    ToolboxComponent,
    ToolboxComponentOption,
    DataZoomComponent,
    DataZoomComponentOption,
    VisualMapComponent,
    VisualMapComponentOption,
    TimelineComponent,
    TimelineComponentOption,
    CalendarComponent,
    CalendarComponentOption
} from 'echarts/components';
import {
    CanvasRenderer
} from 'echarts/renderers';

// 通过 ComposeOption 来组合出一个只有必须组件和图表的 Option 类型
type ECOption = echarts.ComposeOption<
    BarSeriesOption | LineSeriesOption | TitleComponentOption | GridComponentOption | TooltipComponentOption | LegendComponentOption | ToolboxComponentOption | DataZoomComponentOption | VisualMapComponentOption | TimelineComponentOption | CalendarComponentOption | PieSeriesOption
    >;
// 注册必须的组件
echarts.use(
    [TitleComponent, TooltipComponent, GridComponent, BarChart, LineChart, CanvasRenderer, LegendComponent, ToolboxComponent, DataZoomComponent, VisualMapComponent, TimelineComponent, CalendarComponent, PieChart]
);
export default echarts;