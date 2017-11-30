using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Data.Entity;
using System.Linq;

namespace WebApi2Sample.Tests
{
    using System.Threading;
    using System.Threading.Tasks;

    public class FakeDbSet<T> : DbSet<T>, IQueryable, IEnumerable<T>
        where T : class
    {
        private readonly ObservableCollection<T> _data;
        private readonly IQueryable _query;

        public FakeDbSet()
        {
            this._data = new ObservableCollection<T>();
            this._query = this._data.AsQueryable();
        }

        public override ObservableCollection<T> Local
        {
            get { return new ObservableCollection<T>(this._data); }
        }

        Type IQueryable.ElementType
        {
            get { return this._query.ElementType; }
        }

        System.Linq.Expressions.Expression IQueryable.Expression
        {
            get { return this._query.Expression; }
        }

        IQueryProvider IQueryable.Provider
        {
            get { return this._query.Provider; }
        }


        public override T Add(T item)
        {
            this._data.Add(item);
            return item;
        }

        public override T Remove(T item)
        {
            this._data.Remove(item);
            return item;
        }

        public override IEnumerable<T> AddRange(IEnumerable<T> entities)
        {
            foreach (var entity in entities)
            {
                this._data.Add(entity);
            }
            return this._data;
        }

        public override T Attach(T item)
        {
            this._data.Add(item);
            return item;
        }

        public override T Create()
        {
            return Activator.CreateInstance<T>();
        }

        public override TDerivedEntity Create<TDerivedEntity>()
        {
            return Activator.CreateInstance<TDerivedEntity>();
        }

        System.Collections.IEnumerator System.Collections.IEnumerable.GetEnumerator()
        {
            return this._data.GetEnumerator();
        }

        IEnumerator<T> IEnumerable<T>.GetEnumerator()
        {
            return this._data.GetEnumerator();
        }

        public override T Find(params object[] keyValues)
        {
            if (keyValues != null && keyValues.Length > 0)
            {
                var idProperty = typeof(T).GetProperty("Id");
                return this._data.FirstOrDefault(it => idProperty.GetValue(it).Equals(keyValues[0]));
            }

            return null;
        }

        public override Task<T> FindAsync(params object[] keyValues)
        {
            return Task.Run(() => Find(keyValues));
        }

        public override Task<T> FindAsync(CancellationToken cancellationToken, params object[] keyValues)
        {
            return Task.Run(() => Find(keyValues), cancellationToken);
        }
    }
}
