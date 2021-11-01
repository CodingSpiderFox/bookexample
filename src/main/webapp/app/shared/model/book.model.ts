export interface IBook {
  id?: number;
  name?: string;
  price?: number;
}

export const defaultValue: Readonly<IBook> = {};
