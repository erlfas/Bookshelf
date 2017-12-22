import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookshelfcontentComponent } from './bookshelfcontent.component';

describe('BookshelfcontentComponent', () => {
  let component: BookshelfcontentComponent;
  let fixture: ComponentFixture<BookshelfcontentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookshelfcontentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookshelfcontentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
