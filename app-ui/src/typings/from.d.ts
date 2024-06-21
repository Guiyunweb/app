import type { LabelPlacement } from 'naive-ui/es/form/src/interface';

declare namespace From {
  interface Option {
    column: Column[];
    inline?: boolean; // 是否展示为行内表单
    labelPlacement?: LabelPlacement; // 标签显示的位置
  }
  interface Column {
    label: string;
    name: string;
    type: 'textarea' | 'text' | 'password' | 'number' | 'radio' | 'select';
    placeholder?: string;
    notNull?: boolean;
    select?: Select[];
  }
  interface Rules {
    [key: string]: any;
  }
  interface Select {
    label: string;
    value: any;
    checked?: boolean;
  }
}
