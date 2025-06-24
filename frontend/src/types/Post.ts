export interface Interactions {
  likes: number;
  comments: number;
  shares: number;
}

export interface Post {
  id: string;
  content: string;
  publishDate: string;
  author: string;
  interactions: Interactions;
}
