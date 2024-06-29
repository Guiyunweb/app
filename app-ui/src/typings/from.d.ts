import type { LabelPlacement } from 'naive-ui/es/form/src/interface';
import type { SelectMixedOption } from 'naive-ui/es/select/src/interface';

declare namespace From {
  interface Option {
    column: Column[];
    inline?: boolean; // 是否展示为行内表单
    labelPlacement?: LabelPlacement; // 标签显示的位置
  }
  interface Column {
    label: string;
    name: string;
    type: 'textarea' | 'text' | 'password' | 'number' | 'radio' | 'select' | 'date' | 'daterange';
    placeholder?: string;
    notNull?: boolean;
    select?: SelectMixedOption[];
    radio?: Radio[];
    labelWidth?: number;
  }
  interface Rules {
    [key: string]: any;
  }
  interface Radio {
    label: string;
    value: string | number;
  }
}
