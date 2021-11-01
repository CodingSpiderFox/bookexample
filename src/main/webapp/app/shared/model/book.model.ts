import { IAuthor } from 'app/shared/model/author.model';

export interface IBook {
  id?: number;
  name?: string;
  price?: number;
  authors?: IAuthor[];
}

export const defaultValue: Readonly<IBook> = {};
