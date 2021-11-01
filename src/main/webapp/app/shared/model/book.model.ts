import { IAuthor } from 'app/shared/model/author.model';

export interface IBook {
  id?: number;
  price?: number;
  title?: string;
  authors?: IAuthor[];
}

export const defaultValue: Readonly<IBook> = {};
