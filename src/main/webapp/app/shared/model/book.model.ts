import { IAuthor } from 'app/shared/model/author.model';

export interface IBook {
  id?: number;
  name?: string;
  price?: number;
  writtenBies?: IAuthor[];
}

export const defaultValue: Readonly<IBook> = {};
