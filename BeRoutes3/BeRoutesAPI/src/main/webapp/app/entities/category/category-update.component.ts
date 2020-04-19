import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICategory, Category } from 'app/shared/model/category.model';
import { CategoryService } from './category.service';

@Component({
  selector: 'jhi-category-update',
  templateUrl: './category-update.component.html'
})
export class CategoryUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    nameCategory: [],
    cheap: [],
    luxury: [],
    lonely: [],
    friends: [],
    romantic: [],
    kids: [],
    sport: [],
    relaxation: [],
    art: [],
    food: [],
    nature: [],
    city: []
  });

  constructor(protected categoryService: CategoryService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ category }) => {
      this.updateForm(category);
    });
  }

  updateForm(category: ICategory): void {
    this.editForm.patchValue({
      id: category.id,
      nameCategory: category.nameCategory,
      cheap: category.cheap,
      luxury: category.luxury,
      lonely: category.lonely,
      friends: category.friends,
      romantic: category.romantic,
      kids: category.kids,
      sport: category.sport,
      relaxation: category.relaxation,
      art: category.art,
      food: category.food,
      nature: category.nature,
      city: category.city
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const category = this.createFromForm();
    if (category.id !== undefined) {
      this.subscribeToSaveResponse(this.categoryService.update(category));
    } else {
      this.subscribeToSaveResponse(this.categoryService.create(category));
    }
  }

  private createFromForm(): ICategory {
    return {
      ...new Category(),
      id: this.editForm.get(['id'])!.value,
      nameCategory: this.editForm.get(['nameCategory'])!.value,
      cheap: this.editForm.get(['cheap'])!.value,
      luxury: this.editForm.get(['luxury'])!.value,
      lonely: this.editForm.get(['lonely'])!.value,
      friends: this.editForm.get(['friends'])!.value,
      romantic: this.editForm.get(['romantic'])!.value,
      kids: this.editForm.get(['kids'])!.value,
      sport: this.editForm.get(['sport'])!.value,
      relaxation: this.editForm.get(['relaxation'])!.value,
      art: this.editForm.get(['art'])!.value,
      food: this.editForm.get(['food'])!.value,
      nature: this.editForm.get(['nature'])!.value,
      city: this.editForm.get(['city'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategory>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
